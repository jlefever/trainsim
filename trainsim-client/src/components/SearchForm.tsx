import "bulma/css/bulma.css";
import React from "react";
import Stop from "../Stop";
import TrainQuery from "../TrainQuery";
import DateField from "./DateField";
import RangeDropdown from "./RangeDropdown";
import StopDropdown from "./StopDropdown";


interface SearchFormState {
    stops: Stop[];
    query: TrainQuery;
    isRoundTrip: boolean;
}

// https://stackoverflow.com/a/19691491
function addDays(date: Date, days: number) {
    const result = new Date(date);
    result.setDate(result.getDate() + days);
    return result;
};

function isValidQuery(state: SearchFormState) {
    const stops = state.stops.map(s => s.name);
    const query = state.query;
    
    if (!stops.includes(query.source) || !stops.includes(query.target)) {
        return false;
    }

    return !(query.return && query.return <= query.depart);
}

function submit(state: SearchFormState) {
    const query = state.isRoundTrip ? state.query : { ...state.query, return: null };

    const req = new XMLHttpRequest();
    req.open("POST", "/api/query", true);
    req.send(JSON.stringify(query));
}

export default class SearchForm extends React.Component<{}, SearchFormState> {
    constructor(props: {}) {
        super(props);
        this.state = {
            stops: [],
            query: {
                source: "",
                target: "",
                depart: new Date(),
                return: addDays(new Date(), 3),
                travelers: 1
            },
            isRoundTrip: false
        };
    }

    override componentDidMount() {
        fetch("/api/stops")
            .then(res => res.json())
            .then(res => this.setState({ stops: res }));
    }

    override render() {
        return <div className="pt-5">
            <div className="tabs is-centered">
                <ul>
                    <li className={this.state.isRoundTrip ? "" : "is-active"}>
                        <a onClick={_ => this.setState({ isRoundTrip: false })}>One-Way</a>
                    </li>
                    <li className={this.state.isRoundTrip ? "is-active" : ""}>
                        <a onClick={_ => this.setState({ isRoundTrip: true })}>Round-Trip</a>
                    </li>
                </ul>
            </div>
            <div className="columns">
                <div className="column">
                    <StopDropdown
                        name="From"
                        stops={this.state.stops}
                        value={this.state.query.source}
                        onChange={v => this.setState({ query: { ...this.state.query, source: v } })}
                    />
                </div>
                <div className="column">
                    <StopDropdown
                        name="To"
                        stops={this.state.stops}
                        value={this.state.query.target}
                        onChange={v => this.setState({ query: { ...this.state.query, target: v } })}
                    />
                </div>
            </div>
            <div className="columns">
                <div className="column is-5">
                    <DateField
                        name="Depart"
                        min={new Date()}
                        max={this.state.isRoundTrip ? this.state.query.return : null}
                        value={this.state.query.depart}
                        onChange={v => this.setState({ query: { ...this.state.query, depart: v } })}
                    />
                </div>
                <div className="column is-5">
                    <DateField
                        name="Return"
                        min={this.state.query.depart}
                        value={this.state.query.return}
                        onChange={v => this.setState({ query: { ...this.state.query, return: v } })}
                        disabled={!this.state.isRoundTrip}
                    />
                </div>
                <div className="column is-2">
                    <RangeDropdown
                        name="Travelers"
                        icon="fas fa-train"
                        min={1}
                        max={10}
                        value={this.state.query.travelers}
                        onChange={v => this.setState({ query: { ...this.state.query, travelers: v } })}
                    />
                </div>
            </div>
            <div className="columns">
                <div className="column has-text-centered">
                    <button
                        className="button is-medium is-fullwidth is-primary"
                        disabled={isValidQuery(this.state)}
                        onClick={_ => submit(this.state)}
                    >
                        Find Trains
                    </button>
                </div>
            </div>
        </div>;
    }
}