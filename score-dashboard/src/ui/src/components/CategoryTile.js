import { React } from 'react';
import { Link } from 'react-router-dom';

import './CategoryTile.scss';
export const CategoryTile = ({categoryName}) => {


    return (
        <div className="CategoryTile">
            <h1>
                    <Link to={`/categories/${categoryName}`}>
                        {categoryName}
                    </Link>
                </h1>
        </div>
    )
}