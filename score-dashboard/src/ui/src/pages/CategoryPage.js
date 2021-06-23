import { React, useEffect, useState } from 'react';
import {CategorySection} from '../components/CategorySection';
import {UserRankSection} from '../components/UserRankSection'
import './CategoryPage.scss'

export const CategoryPage = () => {
    const [categories, setCategories] = useState([]);

    useEffect(
        () => {
            const fetchCategories = async() => {
                const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/category`);
                const data = await response.json();
                setCategories(data);
            };
            fetchCategories();       
        }, []
    );

    return(
        <div className="CategoryPage">
            <div className="CategorySection">
                <CategorySection categories={categories}/>
            </div>
            <div className="UserRankSection">
                <UserRankSection />
            </div>
        </div>
    );

}