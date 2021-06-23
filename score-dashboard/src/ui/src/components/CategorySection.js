import { React } from  'react';
import {Link} from 'react-router-dom';


export const CategorySection = ({categories}) => {
    return(
        <div className="CategorySection">
            <h4>Category</h4>
            <ul >
            { categories.map(category => (
                <li className="ListItems" key={category.name}>
                    <Link to={`/categories/${category.name}`} >{category.name}</Link>
                </li>
            )) }
            </ul>
        </div>
    )
}