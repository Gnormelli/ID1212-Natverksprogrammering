import {resolveMotionValue} from "framer-motion";

function doThrow(e) {
    throw e;
}

const url = "http://localhost:8081";
const ApiPost = {
    apiCall(params, object) {
        return fetch(url + params, {
        method: "POST", // HTTP method
        crossDomain: true,
        headers: {
          // HTTP headers
            "Content-Type": "application/json",
            "Access-Control-Allow-Headers":
                "Origin, X-Requested-With, " +
                "Content-Type, Accept",
           // "Access-Control-Allow-Origin": "http://localhost:3000",
            //"mode": "no-cors"
            },
            
        body: JSON.stringify(object),
        })
            .then((response) =>
            response.status === 200
                ? response
                : doThrow(
                    new Error(
                    "Status was: " + response.statusText + " " + response.status
                    )
                )
            )
            .then((response) => {
                console.log("3")
                //console.log(response.json())
                console.log("6")
                // if(JSON.stringify(response) === "{}"){
                //     console.log("7")
                //     return ({"tests1": "tests2"})
                // }else
                    console.log("8")
                    return response.json()
            });
        },
        postData(object) {


            const postQueueEndpoint = "/postQueue";
            return ApiPost.apiCall(postQueueEndpoint, object).then((data) => data);
        },
        postLoginInformation(loginForm) {
            const postLoginEndpoint = "/perform_login";
            return ApiPost.apiCall(postLoginEndpoint, loginForm).then((data) => data);
        },
    getLoginInformation(loginForm) {
        const getLoginEndpoint = "/login";
        return ApiPost.apiCall(getLoginEndpoint,loginForm).then((data) => data);
    },tryToLogIn(loginForm) {
        const getLoginEndpoint = "/perform_login";
        return ApiPost.apiCall(getLoginEndpoint,loginForm).then((data) => data);
    },
    };

export default ApiPost;