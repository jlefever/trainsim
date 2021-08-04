import React from "react";
import { GoogleLogin, GoogleLogout } from 'react-google-login';

import "bulma/css/bulma.css";

const client_id = "980475465865-qu5scv4mr1qmqspcn7lj3ft910cgif96.apps.googleusercontent.com"

interface LoginState {
    isLoggedIn: boolean,
    name: string,
    userId: number
}

export default class GoogleLoginField extends React.Component<{}, LoginState> {
    constructor(props: {}) {
        super(props);
        this.loginSuccess = this.loginSuccess.bind(this);
        this.loginFailure = this.loginFailure.bind(this);
        this.logout = this.logout.bind(this);
        this.state = {
            isLoggedIn: false,
            name: "",
            userId: -1
        };
    }

    loginSuccess (response: any) {
        const loginInfo = {
            email: response.profileObj.email
        }
        console.log(loginInfo)
        const body = JSON.stringify(loginInfo);

        fetch("/api/users", { method: "POST", body })
            .then(res => res.json())
            .then(res => {
                console.log(res)
                this.setState({
                    isLoggedIn: true,
                    name: response.profileObj.name,
                    userId: res.id
                })
            })
    }

    logout () {
        console.log("Logout")
        this.setState({
            isLoggedIn: false
        })
    }

    loginFailure (response: any) {
        console.log("Google login error")
        console.log(response)
    }

    override render () {
        if (this.state.isLoggedIn)
            return <div>
                <small> Hello, {this.state.name}! </small>
                <GoogleLogout
                    clientId={client_id}
                    render={renderProps => (
                        <button
                            className="button is-dark has-text-weight-bold"
                            onClick={renderProps.onClick}
                            disabled={renderProps.disabled} >
                            Log out
                        </button >
                    )}
                    buttonText="Logout"
                    onLogoutSuccess={this.logout}
                />
            </div>

        return <GoogleLogin clientId={client_id}
            render={renderProps => (
                <button
                    className="button is-dark has-text-weight-bold"
                    onClick={renderProps.onClick}
                    disabled={renderProps.disabled} >
                    Sign-in with Google
                </button >
            )}
            buttonText="Log in with Google"
            onSuccess={this.loginSuccess}
            onFailure={this.loginFailure}
            cookiePolicy={'single_host_origin'}
        />
    }
}
