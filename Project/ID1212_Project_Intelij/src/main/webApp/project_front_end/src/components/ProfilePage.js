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
import profileData from "../profileData";

export default function ProfilePage(props) {
  const navigate = useNavigate();
  const [profileNumber, setProfileNumber] = React.useState();
  const [profileUrl, setProfileUrl] = React.useState();


  React.useEffect(() => {
    //Get profile picture from database and use instead of static TODO


    //ApiPost.getProfilePicture(post).then(e=>console.log(e));


    setProfileNumber(props.userProfileInfoForUI.profilePictureID);
  }, []);

  const optionsForProfile = profileData.map((info) => (
    <option key={info.id} value={info.id}>
      {info.title}
    </option>
  ));

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
  function getProfilePicture(numberToGet) {


    const profilePictureItem = profileData.find(
      (element) => element.id == numberToGet
    );
    setProfileUrl(profilePictureItem.url);
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
  }

  function logOut() {
    props.logOut();
    navigate("/");
  }

  function test() {

    const h = ["one","two","tre","four"]
    console.log(h.at(1))

    ApiCall.getPictures().then(e=>{
      e.map(el=> el);
    });
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
          {optionsForProfile}
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
      </Flex>
    </Flex>
  );
}
