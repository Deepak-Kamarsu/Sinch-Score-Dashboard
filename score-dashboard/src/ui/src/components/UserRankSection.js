import React, { useMemo, useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { useTable } from 'react-table'
import { COLUMNS } from './Columns'
import './UserRankSection.css'

export const UserRankSection = ({categoryName1}) => {
    const[category, setCategory] = useState({userRankDetails: []});
    const{ categoryName } = useParams();
    useEffect(
        () => {
            const fetchUserRankDetails = async() => {
                const response = await fetch(`http://localhost:8080/category/${categoryName}`);
                const jsonData = await response.json();
                // console.log(jsonData.userRankDetails)
                setCategory(jsonData);
            };
            fetchUserRankDetails();
        }, [categoryName]
        );

    

    const columns = useMemo(() => COLUMNS, [])
    // const data = useMemo(() => category.userRankDetails, [])
    const tableInstance = useTable({
        columns: columns,
        data: category.userRankDetails
    })
    
    const {getTableProps, getTableBodyProps, headerGroups, rows, prepareRow} = tableInstance

    if (!category || !category.name) {
        return <h1>Team not found</h1>
    }
    return(
        <div>
        <h1>{category.name} Score</h1>
        <table {...getTableProps()}>
            <thead>
                {
                    headerGroups.map((headerGroup) => (
                        <tr {...headerGroup.getHeaderGroupProps()}>
                            {headerGroup.headers.map((column) => (
                                <th {...column.getHeaderProps()}>{column.render('Header')}</th>
                            ))}

                        </tr>
                    ))
                }
            </thead>
            <tbody {...getTableBodyProps()}>
                {
                    rows.map(row => {
                        prepareRow(row)
                        return(
                           <tr {...row.getRowProps()}>
                               {
                                   row.cells.map(cell => {
                                       return <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                                   })
                               }
                           </tr> 
                        )
                    })
                }
            </tbody>
        </table>
        </div>
    )
}