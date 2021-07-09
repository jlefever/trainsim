import "bulma/css/bulma.css";
import React from "react";
import Stop from "../Stop";
import StopDropdown from "./StopDropdown";


interface SearchFormState {
    stops: Stop[];
}

export default class SearchForm extends React.Component<{}, SearchFormState> {
    constructor(props: {}) {
        super(props);
        this.state = { stops: [] };
    }

    override componentDidMount() {
        fetch("/api/stops")
            .then(res => res.json())
            .then(res => this.setState({ stops: res }));
    }

    override render() {
        return <form className="pt-5">
            <div className="columns">
                <div className="column">
                    <div className="field">
                        <label className="label">From</label>
                        <StopDropdown stops={this.state.stops} />
                    </div>
                </div>
                <div className="column">
                    <div className="field">
                        <label className="label">To</label>
                        <StopDropdown stops={this.state.stops} />
                    </div>
                </div>
            </div>
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
                        <div className="control has-icons-left">
                            <span className="select" style={{ width: "100%" }}>
                                <select style={{ width: "100%" }}>
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
                                <span className="icon is-small is-left">
                                    <i className="fas fa-user"></i>
                                </span>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            {/* <div className="columns">
                <div className="column is-half">
 
                </div>
                <div className="column">

                </div>
            </div> */}
            <div className="columns">
                <div className="column has-text-centered">
                    <button className="button is-medium is-fullwidth is-primary">Find Trains</button>
                </div>
            </div>
        </form>;
    }
}