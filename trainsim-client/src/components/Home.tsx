import React from "react";

import "bulma/css/bulma.css";
import "@fortawesome/fontawesome-free/css/all.css";

import Banner from "./Banner";

export default () => <>
    <Banner />
    <div className="tabs is-centered">
        <ul>
            <li className="is-active"><a>One-Way</a></li>
            <li><a>Round-Trip</a></li>
        </ul>
    </div>
    <form className="pt-5">
        <datalist id="stations">
            <option>Philadelphia</option>
            <option>Lancaster</option>
            <option>New York City</option>
        </datalist>
        <div className="columns">
            <div className="column is-half">
                <div className="columns">
                    <div className="column">
                        <div className="field">
                            <label className="label">From</label>
                            <div className="control has-icons-left">
                                <input className="input" list="stations" placeholder="Enter a Starting Station" />
                                <span className="icon is-left">
                                    <i className="fas fa-train"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div className="column">
                        <div className="field">
                            <label className="label">To</label>
                            <div className="control has-icons-left">
                                <input className="input" list="stations" placeholder="Enter a Destination Station" />
                                <span className="icon is-left">
                                    <i className="fas fa-train"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="column">
                <div className="columns">
                    <div className="column is-5">
                        <div className="field">
                            <label className="label" htmlFor="departure">Depart</label>
                            <div className="control has-icons-left">
                                <input className="input" name="departure" type="date" value="2020-07-01"
                                    min="2020-07-01" />
                                <span className="icon is-left">
                                    <i className="fas fa-calendar"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div className="column is-5">
                        <div className="field">
                            <label className="label" htmlFor="arrival">Return</label>
                            <div className="control has-icons-left">
                                <input className="input" type="text" value="No Return Trip" disabled />
                                <span className="icon is-left">
                                    <i className="fas fa-calendar"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div className="column is-2">
                        <div className="field">
                            <label className="label">Travelers</label>
                            <div className="control">
                                <div className="select">
                                    <select>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div className="columns">
            <div className="column has-text-centered">
                <button className="button is-medium is-fullwidth is-primary">Find Trains</button>
            </div>
        </div>
    </form>
</>;