import React from "react";
import { Navigate, useNavigate } from "react-router-dom";
import {
  Button,
  Flex,
  Checkbox,
  Heading,
  Stack,
  Input,
  Image,
  useColorModeValue,
  Select, FormControl,
} from "@chakra-ui/react";
import ApiCall from "../ApiInterface/ApiCall";
import ApiPost from "../ApiInterface/ApiPost";

export default function ProfilePage(props) {
  const navigate = useNavigate();
  const [profileUrl, setProfileUrl] = React.useState();
  const [profilePicturesFull, setProfilePicturesFull] = React.useState();
  const [optionsToChooseForProfile, setOptionsToChooseForProfile] = React.useState();
  const [groupsUserIsPartOf, setGroupsUserIsPartOf] = React.useState([0]);
  const [optionsOfChats, setOptionsOfChats] = React.useState();
  const [posts, setPosts] = React.useState()
  const [messageToSend, setMessageToSend] = React.useState("")

  React.useEffect(() => {
    ApiCall.getPosts().then(e =>{
      const thePosts = e.map(e => <Flex mb={3} position="screenLeft" key={e.id} p={3} boxSize rounded={6} backgroundColor={"whiteAlpha.400"}> Poster {e.fromUser}:  {e.postText} </Flex>)
      setPosts(thePosts)
    })
    ApiCall.getPictures().then(e => {
      setProfilePicturesFull(e);

      setOptionsToChooseForProfile(e.map(el => <option key={el.id} value={el.id}>
        {el.title}
      </option>))
      const profilePictureItem = e.find(
          (element) => element.id == props.userProfileInfoForUI.profilePictureID
      );
      setProfileUrl(profilePictureItem.picture)
    });
      const post = {
        id: props.userProfileInfoForUI.theRealID,
        username: "placeholder",
        password: "placeholder",
        email: "hi@gmail.com",
        locked: false,
        enabled: true
      };

    ApiPost.getChatsUserIsPartOf(post).then(e => {
      if(e.id != 'User does not exist'){
        setGroupsUserIsPartOf(e)
      }else{
        setGroupsUserIsPartOf([0])
      }
    })
  }, []);

  React.useEffect(() => {
    ApiCall.getAllConversations().then(e => {
      if(groupsUserIsPartOf != null){
        const filterdList = groupsUserIsPartOf.map(item => item.id)
        const checkmarks = e.map(item=>{
          if(filterdList.includes(item.id)){//if item is in groupsUserIsPartOf
            return (
                <Checkbox
                    value={item.id}
                    colorScheme="green"
                    key={item.id}
                    onChange={updateMemebership}
                    defaultChecked
                >
                  {item.name}
                </Checkbox>
            );
          }else{
            return (
                <Checkbox value={item.id} colorScheme="green" key={e.length + item.id} onChange={updateMemebership}>
                  {item.name}
                </Checkbox>
            );
          }
        })
        setOptionsOfChats(checkmarks);
      }else{
        const checkmarks =  e.map(item=> {
          return (
              <Checkbox value={item.id} colorScheme="green" key={item.id} onChange={updateMemebership}>
                {item.name}
              </Checkbox>
          );
        })
        setOptionsOfChats(checkmarks);
      }

    })
  }, [groupsUserIsPartOf]);

  function updateMemebership(event) {

    const id = event.target.value;
    props.updateChatsMembershipFunction(id);
  }


  function getProfilePicture(numberToGet) {

    const profilePictureItem = profilePicturesFull.find(
        (element) => element.id == numberToGet
    );
    setProfileUrl(profilePictureItem.picture);
  }


  const formBackground = useColorModeValue("gray.100", "gray.700");
  if (!props.authorized) {
    return <Navigate to="/" />;
  }

  function goToChat() {
    navigate("/chat");
  }

  function changeProfile(event) {
    const value = event.target.value;
    if(value != ""){
      props.changeUserInfoFunction("profilePictureID", value);
      getProfilePicture(value);
      const post = {
        username: props.userProfileInfoForUI.id,
        profilePicture: {id: value, picture: "0", title: "0"},
      };
      ApiPost.changeProfilePicture(post).then((e) => e)
    }

  }
  function logOut() {
    props.logOut();
    navigate("/");
  }

  function updateMessage(event) {
    const textMessage = event.target.value;
    setMessageToSend(textMessage);
  }

  function sendPost() {
    const post = {
      fromUser: props.userProfileInfoForUI.id,
      postText: messageToSend,
    };
    ApiPost.sendPost(post).then(e => console.log("Post sent"))
    setMessageToSend("");
  }
  const adminButton = <Button
      width="100%"
      colorScheme="blue"
      position="top"
      onClick={goToAdmin}
      mb={3}
  >
    {" "}
    Go to admin page
  </Button>

  function goToAdmin(){
    navigate("/admin");
  }
  return (
    <Flex
      height="100vh"
      alignItems="top"
      justifyContent="center"
      backgroundColor="orange.400"
    >
      <Image
        onClick={goToChat}
        src="/pictures/chaticon.png"
        boxSize="100px"
        alignItems="left"
      />{" "}
      <Flex direction="column" background={formBackground} p={12} rounded={6}>
        {props.userProfileInfoForUI.role == 1 && adminButton}
        <Select placeholder="Select profile picture" onChange={changeProfile}>
          {optionsToChooseForProfile}
        </Select>{" "}
        <Image src={profileUrl} boxSize="100px" />
        <Heading>What chats do you wanna join</Heading>
        <Stack spacing={2} direction="column">
          {optionsOfChats}
        </Stack>
        <Button
          width="100%"
          colorScheme="blue"
          position="top"
          onClick={goToChat}
          mb={3}
        >
          {" "}
          Go to chats
        </Button>
        <h1>{props.userProfileInfoForUI.id}, would you like to send a post?</h1>

          <FormControl>
            <Input
                type="text"
                placeholder="What are you feeling atm"
                onChange={updateMessage}
                name="messageToSend"
                value={messageToSend}
            />
            <Button type="submit" onClick={sendPost} backgroundColor={"blue.300"}>
              Send
            </Button>
         </FormControl>


       <Flex overflowY="scroll" height = "300px" backgroundColor={"teal.300"} p={6} rounded={6} direction="column">
         {posts}

       </Flex>
        <Button
          width="100%"
          colorScheme="blue"
          position="top"
          onClick={logOut}
          mb={3}
        >
          {" "}
          Log out
        </Button>
      </Flex>
    </Flex>
  );
}
