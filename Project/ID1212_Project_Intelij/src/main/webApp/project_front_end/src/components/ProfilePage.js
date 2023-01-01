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

  React.useEffect(() => {
    console.log(props.authorized)
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

  }, []);


  function getProfilePicture(numberToGet) {

    const profilePictureItem = profilePicturesFull.find(
        (element) => element.id == numberToGet
    );
    setProfileUrl(profilePictureItem.picture);
  }





  const chatOptions = chatData.map((e) => {
    const userInfo = props.userProfileInfoForUI;
    // console.log(userInfo);
    if (e.members.includes(userInfo.id)) {
      return (
        <Checkbox
          value={e.id}
          colorScheme="green"
          key={e.id}
          onChange={hold}
          defaultChecked
        >
          {e.title}
        </Checkbox>
      );
    } else {
      return (
        <Checkbox value={e.id} colorScheme="green" key={e.id} onChange={hold}>
          {e.title}
        </Checkbox>
      );
    }
  });

  function hold(event) {
    //console.log(event);
    const id = 0;
    props.updateChatsMembershipFunction(id);
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

  function test() {

    console.log(props.userProfileInfoForUI.theRealID)
  }

  function test2() {
    console.log(profileUrl)
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
          {chatOptions}
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
        <Button
          width="100%"
          colorScheme="blue"
          position="top"
          onClick={test}
          mb={3}
        >
          {" "}
          test
        </Button>
        <Button
            width="100%"
            colorScheme="blue"
            position="top"
            onClick={test2}
            mb={3}
        >
          {" "}
          test2
        </Button>
      </Flex>
    </Flex>
  );
}
