import "@fortawesome/fontawesome-free/css/all.css";
import "bulma/css/bulma.css";
import React from "react";
import Banner from "./Banner";
import SearchForm from "./SearchForm";

export default () => <>
    <Banner />
    <div className="tabs is-centered">
        <ul>
            <li className="is-active"><a>One-Way</a></li>
            <li><a>Round-Trip</a></li>
        </ul>
    </div>
    <SearchForm />
</>;