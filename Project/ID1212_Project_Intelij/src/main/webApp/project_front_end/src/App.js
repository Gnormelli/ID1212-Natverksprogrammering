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
import ApiPost from "./ApiInterface/ApiPost";


export default function App() {
  const navigate = useNavigate();
  const [authorized, setAuthorized] = React.useState(false);
  const [chatsUserIsMemberOf, setChatsUserIsMemberOf] = React.useState([]);
  const [userProfileInfoForUI, setUserProfileInfoForUI] = React.useState({
    id: "Nobody",
    profilePictureID: 1,
    theRealID: 0,
  });



  function updateMembership(chatId){
    if(chatsUserIsMemberOf.includes(chatId)){
      const h = chatsUserIsMemberOf.filter()
      setChatsUserIsMemberOf(h);
      //the api call
    }else{
      setChatsUserIsMemberOf(prevState => {
        return prevState.push(chatId)
      });
    }

  }

  function test(e) {
    //console.log(userProfileInfoForUI.profilePictureID);
  }

  function createProfile(username, id) {
    //Create profile in database TODO (username/password, set profile picture defult to 1)
    setAuthorized(true);
    setUserProfileInfoForUI({
      id: username,
      profilePictureID: 1,
      theRealID: id,
    });
  }

  function setFullUser(info){
    setAuthorized(true);
    setUserProfileInfoForUI(info)
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

    const post = {
        compositeKey: {
          user: {
            id: userProfileInfoForUI.theRealID},
          conversation:{id: chatID},
        }
      }

      ApiPost.updateMembership(post).then(e=> {
        if(e.id.localeCompare('Done: Group Member added') === 0){
            console.log("Joined group")
        }else{
          console.log("Left chat")
        }
      })


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
          element={<LogInPage authorized={authorized} setFullUserGlobal={setFullUser} />}
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
