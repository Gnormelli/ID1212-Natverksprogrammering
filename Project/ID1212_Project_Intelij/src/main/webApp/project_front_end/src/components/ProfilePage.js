import React from "react";
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
  Select,
} from "@chakra-ui/react";
import ApiCall from "../ApiInterface/ApiCall";
import ApiPost from "../ApiInterface/ApiPost";

export default function ProfilePage(props) {
  const navigate = useNavigate();
  const [profileNumber, setProfileNumber] = React.useState();
  const [profileUrl, setProfileUrl] = React.useState();
  const [profilePicturesFull, setProfilePicturesFull] = React.useState();
  const [optionsToChooseForProfile, setOptionsToChooseForProfile] = React.useState();
  const [groupsUserIsPartOf, setGroupsUserIsPartOf] = React.useState();
  const [optionsOfChants, setOoptionsOfChants] = React.useState();

  React.useEffect(() => {

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
    ApiPost.getChatsUserIsPartOf(post).then(e => setGroupsUserIsPartOf(e))

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
                <Checkbox value={item.id} colorScheme="green" key={item.id} onChange={updateMemebership}>
                  {item.name}
                </Checkbox>
            );
          }
        })
        setOoptionsOfChants(checkmarks);
      }else{
        console.log("hi")
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

  function changeProfile(event) { //wtf happend here
    const value = event.target.value;
    props.changeUserInfoFunction("profilePictureID", value);
    setProfileNumber(value);
    getProfilePicture(value);


    const post = {
      username: props.userProfileInfoForUI.id,
      profilePicture: {id: value, picture: "0", title: "0"},

    };
    ApiPost.changeProfilePicture(post).then((e) => console.log(e))

  }
  function logOut() {
    props.logOut();
    navigate("/");
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
          {optionsOfChants}
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
