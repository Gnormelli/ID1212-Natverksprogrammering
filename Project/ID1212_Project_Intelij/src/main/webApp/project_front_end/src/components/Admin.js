import React, {useState } from "react";
import { Form, Navigate, useNavigate } from "react-router-dom";
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
  const [newChatName, setNewChatName] = React.useState("");
  function goToChat() {
    navigate("/chat");
  }
  const formBackground = useColorModeValue("gray.100", "gray.700");

  if (!props.authorized || props.userProfileInfoForUI.role != 1) {
    props.logOut();
    return <Navigate to="/" />;
  }

  function logOut() {
    props.logOut();
    navigate("/");
  }

  function updateMessage(event) {
    const textMessage = event.target.value;
    setNewChatName(textMessage);
  }
  function createNewChat() {
    const post = {
      name: newChatName
    };
    ApiPost.createNewChat(post).then(e => console.log("Chat created"))
    setNewChatName("");
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

          <h1>{props.userProfileInfoForUI.id}, do you want to create a new chat?</h1>



          <FormControl>
            <Input
                type="text"
                placeholder="What are you feeling atm"
                onChange={updateMessage}
                name="messageToSend"
                value={newChatName}
            />
            <Button type="submit" onClick={createNewChat} backgroundColor={"blue.300"}>
              Create new chat
            </Button>
          </FormControl>

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
