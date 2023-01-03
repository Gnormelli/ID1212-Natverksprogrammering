import React, {useState } from "react";
import { Form, Navigate, useNavigate } from "react-router-dom";
import chatData from "../chatData";
import {
  Button,
  Flex,
  Checkbox,
  Heading,
  Stack,
  Input,
  Image,
  useColorMode,
  useColorModeValue,
  Select, FormControl,
} from "@chakra-ui/react";
import ApiCall from "../ApiInterface/ApiCall";
import ApiPost from "../ApiInterface/ApiPost";

export default function ProfilePage(props) {
  const navigate = useNavigate();
  const [profileNumber, setProfileNumber] = React.useState();
  const [profileUrl, setProfileUrl] = React.useState();
  const [profilePicturesFull, setProfilePicturesFull] = React.useState();
  const [optionsToChooseForProfile, setOptionsToChooseForProfile] = React.useState();
  const [groupsUserIsPartOf, setGroupsUserIsPartOf] = React.useState([0]);
  const [optionsOfChats, setOptionsOfChats] = React.useState();
  const [posts, setPosts] = React.useState()
  const [messageToSend, setMessageToSend] = React.useState()

  React.useEffect(() => {

   // ApiCall.getPosts().then(el => {
     const item = [{id: 1, fromUser: "david", postText: "A message"},{id: 2, fromUser: "corry", postText: "A mege"},{id: 3, fromUser: "dd", postText: "A age"},{id: 4, fromUser: "ad", postText: "A mess"},{id: 5, fromUser: "david", postText: "A message"},{id: 6, fromUser: "corry", postText: "A mege"},{id: 7, fromUser: "dd", postText: "A age"},{id: 8, fromUser: "ad", postText: "A mess"}]

    ApiCall.getPosts().then(e =>{
      const thePosts = e.map(e => <Flex mb={3} position="screenLeft" key={e.id} p={3} boxSize rounded={6} backgroundColor={"whiteAlpha.400"}> Poster {e.fromUser}:  {e.postText} </Flex>)
      setPosts(thePosts)
    })



   // })

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
    const post ={
      id: props.userProfileInfoForUI.theRealID
    }
    ApiPost.getChatsUserIsPartOf(post).then(e => {

      if(e.id != 'User does not exist'){
        setGroupsUserIsPartOf(e)
      }else{

        setGroupsUserIsPartOf([0])
      }

    })

  }, []);



  function useForceUpdate(){
    const [value, setValue] = useState(0); // integer state
    return () => setValue(value => value + 1); // update state to force render
    // An function that increment 👆🏻 the previous state like here
    // is better than directly setting `value + 1`
  }


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



  // const useConversations = () => {
  //   return ApiCall.getAllConversations()
  // }
  //
  // const conversations = useConversations().then(e => e.map((conversation) => <Checkbox/>)).then(e=> console.log(e))


  // const useConversations = () => {
  //   url = "..."
  //   return (
  //       fetch(url)
  //           .then((e) => e.json())
  //           .catch((error) => console.log(error))
  //   )
  // }

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
  function hold(){
    console.log(optionsOfChats)

  }



  const formBackground = useColorModeValue("gray.100", "gray.700");
  if (!props.authorized) {
    return <Navigate to="/" />;
  }

  function goToChat() {
    navigate("/chat");
  }

  function changeProfile(event) { //wtf happend here
    const value = event.target.value;
    props.changeUserInfoFunction("profilePictureID", value);
    setProfileNumber(value);
    getProfilePicture(value);


    const post = {
      username: props.userProfileInfoForUI.id,
      profilePicture: {id: value, picture: "0", title: "0"},

    };
    ApiPost.changeProfilePicture(post).then((e) => e)

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
        <Button
            width="100%"
            colorScheme="blue"
            position="top"
            onClick={hold}
            mb={3}
        >
          {" "}
          test
        </Button>
        <h1>Send a post</h1>

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
