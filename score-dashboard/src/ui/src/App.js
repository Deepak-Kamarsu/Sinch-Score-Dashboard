import './App.scss';
// import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import {HashRouter as Router, Route, Switch} from 'react-router-dom'
import {HomePage} from './pages/HomePage'
import {CategoryPage} from './pages/CategoryPage'


function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route path="/categories/:categoryName">
            <CategoryPage />
          </Route>
          <Route path="/">
            <HomePage />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
