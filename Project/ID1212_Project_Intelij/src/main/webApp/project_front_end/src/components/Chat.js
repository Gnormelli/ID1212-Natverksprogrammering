import React from "react";
import { Navigate, useNavigate } from "react-router-dom";
import {
  Button,
  Flex,
  Heading,
  Input,
  Image,
  useColorMode,
  useColorModeValue,
  Spacer,
  theme,
  Topbar,
  Avatar,
  FormControl,
  Text,
} from "@chakra-ui/react";

import ApiPost from "../ApiInterface/ApiPost";

export default function Chat(props) {
  const navigate = useNavigate();
  const formBackground = useColorModeValue("gray.100", "gray.700");
  const [whatChatToShow, setWhatChatToShow] = React.useState(0);
  const [whatGroupToShow, setWhatGroupToShow] = React.useState(1);
  const [messageToSend, setMessageToSend] = React.useState("");
  const [messagesToShow, setMessagesToShow] = React.useState();
  const [displayName, setDisplayName] = React.useState("Error")
  const [currentChat, setCurrentChat] = React.useState(0);



  React.useEffect(() => {
    getChatUserIsPartOf()
  }, []);

  React.useEffect(() => {
    const requestLoop = setInterval(function(){ //Dont remove
      getMessages()
    }, 1000);
    return () => {
      clearInterval(requestLoop);
    };
  }, [whatChatToShow]) ;

  function switchChat(event) {
    setDisplayName(event.target.innerText)
    setWhatChatToShow(event.target.value);
    setCurrentChat(event.target.value);
    getMessagesWithValue(event.target.value)

  }
  function getMessagesWithValue(value){
    const post = {
      fk_conversation: {id: value, name: "hold"}
    };
    ApiPost.getMessagesFromChat(post).then(e=> {
      const textMessagesElement = e.map(el=> {
        if(el.fromUser !== props.currentUser ){
          return (
              <Flex
                  key={el.id}
                  background="red.200"
                  width="fit-content"
                  minWidth="100px"
                  borderRadius="lg"
                  p={3}
                  alignSelf="flex-end"
              >
                <Text>
                  From {el.fromUser}: {el.messageText}
                </Text>
              </Flex>
          );
        }else{
          return (
              <Flex
                  key={el.id}
                  background="green.200"
                  width="fit-content"
                  minWidth="100px"
                  borderRadius="lg"
                  p={3}
              >
                <Text>{el.messageText}</Text>
              </Flex>
          );
        }
      })
      setMessagesToShow(textMessagesElement);
    })
  }

  function getChatUserIsPartOf(){
    const post = {
      id: props.userProfileInfoForUI.theRealID
    };

    ApiPost.getChatsUserIsPartOf(post).then(e=> {
      if(((typeof e.id)).localeCompare("string") !== 0){
        const chats = e.map((el) => {
          return (
              <Button key={el.id} onClick={switchChat} value={el.id}>
                {" "}
                {el.name}{" "}
              </Button>
          );
        })

        setWhatGroupToShow(chats)
      }else{
        setWhatGroupToShow([1])
      }

    })
  }
  function navigateToProfile() {
    navigate("/profile");
  }

  function test(){
    getMessages();
  }

  function getMessages() {
    const post = {
      fk_conversation: {id: currentChat, name: "hold"}
    };
    ApiPost.getMessagesFromChat(post).then(e=> {
      const textMessagesElement = e.map(el=> {
        if(el.fromUser !== props.currentUser ){
          return (
              <Flex
                  key={el.id}
                  background="red.200"
                  width="fit-content"
                  minWidth="100px"
                  borderRadius="lg"
                  p={3}
                  alignSelf="flex-end"
              >
                <Text>
                  From {el.fromUser}: {el.messageText}
                </Text>
              </Flex>
          );
        }else{
          return (
              <Flex
                  key={el.id}
                  background="green.200"
                  width="fit-content"
                  minWidth="100px"
                  borderRadius="lg"
                  p={3}
              >
                <Text>{el.messageText}</Text>
              </Flex>
          );
        }
      })
      setMessagesToShow(textMessagesElement);

    })

  }

  function updateMessage(event) {
    const textMessage = event.target.value;

    setMessageToSend(textMessage);
  }

  function sendMessage() {

    if(messageToSend.localeCompare ("")){
      const post = {
        fromUser: props.userProfileInfoForUI.id,
        fk_conversation: {id: whatChatToShow, name: "Chat with friends"},
        messageText: messageToSend,
      };
      ApiPost.sendMessage(post).then(e =>getMessages())
      setMessageToSend("");
    }
  }

  if (!props.authorized) {
    return <Navigate to="/" />;
  }

  const chatWindow = (
      <Flex background="red.200" flex={10} width="100%" height="100vh">
        <Flex direction="column" background={"gray.100"} flex={100}>
          <Flex h="100px" background={"blue.100"} align="center" p={5}>
            <Avatar src="" />

            <Heading size="lg">{displayName}</Heading>
          </Flex>

          <Flex
              direction="column"
              background={"gray.100"}
              flex={1}
              overflowX="scroll"
          >
            {messagesToShow}
          </Flex>

          <FormControl>
            <Input
                type="text"
                placeholder="Type text to send"
                onChange={updateMessage}
                name="messageToSend"
                value={messageToSend}
            />
            <Button type="submit" onClick={sendMessage}>
              Send
            </Button>
          </FormControl>
        </Flex>
      </Flex>
  );
  return (
      <Flex
          height="100vh"
          alignItems="top"
          justifyContent="left"
          backgroundColor="orange.400"
      >
        <Flex
            direction="column"
            background={formBackground}
            p={12}
            rounded={6}
            position="fixed"
            height="100%"
        >
          <Button
              className="toProfileButton"
              width="100%"
              colorScheme="blue"
              onClick={navigateToProfile}
              mb={3}
          >
            {" "}
            To profile
          </Button>
          <Button
              className="toProfileButton"
              width="100%"
              colorScheme="blue"
              onClick={test}
              mb={3}
          >
            {" "}
            test
          </Button>
          {(whatGroupToShow !== 1) && whatGroupToShow}
        </Flex>
        <Spacer />

        <Flex direction="column">{whatChatToShow !== 0 && chatWindow}</Flex>
      </Flex>
  );
}