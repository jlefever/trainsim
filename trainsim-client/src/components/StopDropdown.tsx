import "bulma/css/bulma.css";
import React from "react";
import Stop from "../Stop";

export interface StopDropdownProps {
    stops: Stop[]
};

export default (props: StopDropdownProps) => <div className="control has-icons-left">
    <span className="select" style={{ width: "100%" }}>
        <select style={{ width: "100%" }}>
            {props.stops.map(s => <option key={s.otpId} value={s.otpId}>{s.name}</option>)}
        </select>
        <span className="icon is-small is-left">
            <i className="fas fa-train"></i>
        </span>
    </span>
</div>;