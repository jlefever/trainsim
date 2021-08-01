import React from "react";
import GoogleLogin from "react-google-login";

import "bulma/css/bulma.css";

import Home from "./Home";

const client_id = "980475465865-qu5scv4mr1qmqspcn7lj3ft910cgif96.apps.googleusercontent.com"

function handleCredentialResponse(response: object) {
    console.log(response)
    const body = JSON.stringify(response);

    fetch("/api/users", { method: "POST",body })
        .then(res => console.log(res.statusText))
}

export default () => <section className="section mt-1 pt-1">
    <div className="container is-max-widescreen">
        <nav aria-label="main navigation" className="navbar is-white mt-5 mb-5" role="navigation">
            <div className="navbar-brand">
                <a className="navbar-item has-text-weight-bold" href="/">
                    <span>trainsim</span>
                </a>
            </div>
            <div className="navbar-menu" id="navbar">
                <div className="navbar-end">
                    <div className="navbar-item">
                        <div className="buttons">
                            <a className="button is-dark has-text-weight-bold" href="#">Sign up</a>
                            <a className="button is-light has-text-weight-bold" href="#">Log in</a>

                            <GoogleLogin
                                clientId={client_id}
                                buttonText="Log in with Google"
                                onSuccess={handleCredentialResponse}
                                onFailure={handleCredentialResponse}
                                cookiePolicy={'single_host_origin'}
                            />

                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <Home />
        <footer className="footer has-background-white">
            <nav className="level">
                <div className="level-left">
                    <small className="level-item"><a className="has-text-dark" href="/">PseudoTrain</a></small>
                    <small className="level-item">&copy; 2021</small>

                </div>
                <div className="level-right">
                    <small className="level-item"><a className="has-text-dark" href="#">Privacy</a></small>
                    <small className="level-item"><a className="has-text-dark" href="#">FAQs</a></small>
                    <small className="level-item"><a className="has-text-dark" href="#">Email support</a></small>
                </div>
            </nav>
        </footer>
    </div>
</section>;