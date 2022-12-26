import React from "react";
import { Route, Routes } from "react-router-dom";
import LogInPage from "./components/LogInPage";
import ProfilePage from "./components/ProfilePage";
import { ChakraProvider } from "@chakra-ui/react";
import SignUp from "./components/SignUp";
import Chat from "./components/Chat";
import chatData from "./chatData";
import profileData from "./profileData";
import { useNavigate } from "react-router-dom";

export default function App() {
  const navigate = useNavigate();
  const [authorized, setAuthorized] = React.useState(true);
  const [userProfileInfoForUI, setUserProfileInfoForUI] = React.useState({
    id: "Nobody",
    profilePictureID: 1,
  });

  function test(e) {
    console.log(userProfileInfoForUI.profilePictureID);
  }

  function createProfile(username, password) {
    //Create profile in database TODO (username/password, set profile picture defult to 1)
    setAuthorized(true);
    setUserProfileInfoForUI({
      id: username,
      profilePictureID: 1,
    });
  }

  function changeUserInfo(name, value) {
    //Send to database update TODO
    setUserProfileInfoForUI((prevValues) => {
      return {
        ...prevValues,
        [name]: value,
      };
    });
  }

  function updateChatsMembership(chatID) {
    //send info to database about who joied what TODO
  }

  function tryToLogIn() {
    const login = !authorized; //this is the database call TODO

    setAuthorized((prevValue) => !prevValue); //Taken from login TODO
    if (login) {
      //database call to get information TODO

      setUserProfileInfoForUI({
        id: "Obi-Wan",
        profilePictureID: 3,
        chatsPartOf: [1, 4],
      });
      console.log("Login succseded");
      navigate("/profile");
    } else {
      console.log("login failed");
      navigate("/");
    }
  }

  function logOut() {
    setUserProfileInfoForUI({
      id: 0,
      profilePictureID: 1,
    });
    setAuthorized(false);
  }

  return (
    <ChakraProvider>
      <Routes>
        <Route
          exact
          path="/"
          element={<LogInPage logIn={tryToLogIn} authorized={authorized} />}
        />
        <Route
          exact
          path="/signUp"
          element={
            <SignUp
              authorized={authorized}
              createProfileFunction={createProfile}
            />
          }
        />
        <Route
          exact
          path="/profile"
          element={
            <ProfilePage
              test={test}
              authorized={authorized}
              logIn={tryToLogIn}
              logOut={logOut}
              changeUserInfoFunction={changeUserInfo}
              userProfileInfoForUI={userProfileInfoForUI}
              updateChatsMembershipFunction={updateChatsMembership}
            />
          }
        />
        <Route
          exact
          path="/chat"
          element={
            <Chat
              authorized={authorized}
              currentUser={userProfileInfoForUI.id}
              logOut={logOut}
              userProfileInfoForUI={userProfileInfoForUI}
            />
          }
        />
      </Routes>
    </ChakraProvider>
  );
}
