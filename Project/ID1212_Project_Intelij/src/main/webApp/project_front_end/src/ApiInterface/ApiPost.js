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
            "Content-Type": "application/json",
            "Access-Control-Allow-Headers":
                "Origin, X-Requested-With, " +
                "Content-Type, Accept",
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
    },changeProfilePicture(loginForm) {
        const getLoginEndpoint = "/update_profile_picture";
        return ApiPost.apiCall(getLoginEndpoint,loginForm).then((data) => data);
    },
    tryToLogIn(loginForm) {
        const getLoginEndpoint = "/perform_login";
        return ApiPost.apiCall(getLoginEndpoint,loginForm).then((data) => data);
    },updateMembership(loginForm) {
        const getLoginEndpoint = "/update_membership";
        return ApiPost.apiCall(getLoginEndpoint,loginForm).then((data) => data);
    },
    createUser(loginForm) {
        const getLoginEndpoint = "/create_user";
        return ApiPost.apiCall(getLoginEndpoint,loginForm).then((data) => data);
    },
    sendMessage(loginForm) {
        const getLoginEndpoint = "/send_message";
        return ApiPost.apiCall(getLoginEndpoint, loginForm).then((data) => data);
    },
    getMessagesFromChat(loginForm) {
        const getLoginEndpoint = "/get_message";
        return ApiPost.apiCall(getLoginEndpoint,loginForm).then((data) => data);
    },
    getChatsUserIsPartOf(loginForm) {
        const getLoginEndpoint = "/conversations_by_userID";
        return ApiPost.apiCall(getLoginEndpoint,loginForm).then((data) => data);
    },
    sendPost(loginForm) {
    const getLoginEndpoint = "/send_post";
    return ApiPost.apiCall(getLoginEndpoint,loginForm).then((data) => data);
    },
    createNewChat(loginForm) {
        const getLoginEndpoint = "/new_conversation";
        return ApiPost.apiCall(getLoginEndpoint,loginForm).then((data) => data);
    },
    };

export default ApiPost;

