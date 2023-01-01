function doThrow(e) {
  throw e;
}

const url = "http://localhost:8081";

const ApiCall = {
  apiCall(params) {
    return fetch(url + params, {
      method: "GET", // HTTP method
      crossDomain: true,
      headers: {
        // HTTP headers
        "Content-Type": "application/json",
        "Access-Control-Allow-Headers":
          "Origin, X-Requested-With, Content-Type, Accept",
      },
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

       // console.log(response.json())
        return response.json()

      });
  },
  getData() {
    const getQueueEndpoint = "/getQueue";
    return ApiCall.apiCall(getQueueEndpoint).then((data) => data);
  },
  getPictures() {
    const getQueueEndpoint = "/get_profile_picture";
    return ApiCall.apiCall(getQueueEndpoint).then((data) => data);
  },
  getAllConversations() {
    const getAllConversations = "/get_all_conversations";
    return ApiCall.apiCall(getAllConversations).then((data) => data);
  },




};

export default ApiCall;
