package sinch.scoredashboard.data;



import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import sinch.scoredashboard.model.UserRank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableBatchProcessing
public class UserRankBatchConfig {

    private final String[] FIELD_NAMES = new String[]{"categoryName", "userName", "rank", "level", "xp"};
    private static final Logger log = LoggerFactory.getLogger(UserRankJobCompletionNotificationListener.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<UserRankInput> reader() {
        return new FlatFileItemReaderBuilder<UserRankInput>().name("UserRankItemReader")
                .resource(new ClassPathResource("user_rank.csv")).delimited()
                .names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<UserRankInput>() {
                    {
                        setTargetType(UserRankInput.class);
                    }
                }).build();
    }

    @Bean
    public UserRankDataProcessor processor() {
        return new UserRankDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<UserRank> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<UserRank>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO user_rank (id, category_name, user_name,rank, level, xp)"
                + " VALUES (:id, :categoryName, :userName, :rank, :level, :xp)").dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserRankJob(UserRankJobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory
            .get("importUserRankJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<UserRank> writer) {
        try
        {
            return stepBuilderFactory
            .get("step1").<UserRankInput, UserRank>chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer)
            .build();
        }
        catch(Exception e)
        {
            log.info("Something went wrong in transferring the data from csv to db!!!");
            throw e;
        }
    }

}