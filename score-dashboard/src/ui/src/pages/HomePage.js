import { React, useEffect, useState } from 'react';
import './HomePage.scss';
import { CategoryTile } from '../components/CategoryTile';


export const HomePage = () => {
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

    return (
        <div className="HomePage">
            <div className="header-section">
                <h1 className="app-name">Sinch Score Dashboard</h1>
            </div>
            <div className="category-grid">
                { categories.map(category => <CategoryTile key={category.name} categoryName={category.name} />)}
            </div>
        </div>
        
    );
}