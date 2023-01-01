import React from "react";
import chatData from "../chatData";
import userData from "../userData";
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
import ApiCall from "../ApiInterface/ApiCall";
import ApiPost from "../ApiInterface/ApiPost";

export default function Chat(props) {
  const navigate = useNavigate();
  const formBackground = useColorModeValue("gray.100", "gray.700");
  const [whatChatToShow, setWhatChatToShow] = React.useState(1);
  const [messageToSend, setMessageToSend] = React.useState("");
  const [currentGroupMembers, setCurrentGroupMembers] = React.useState([
    0,
    "Nobody",
  ]);
  const [currentChat, setCurrentChat] = React.useState({
    id: 0,
    title: "",
    messages: [{ message: "", Sender: 0, orderNumber: 1 }],
    members: [0],
  });





  React.useEffect(() => {
    //console.log(userData.find((user) => user.id === 1));
    var users = [];
    currentChat.members.map((e) => {
      users.push(userData.find((user) => user.id === e));
    });

    setCurrentGroupMembers(users);
  }, [currentChat]);




  function switchChat(event) {
    const buttonClicked = event.target.innerText;
    const chatItem = chatData.find((element) => element.title == buttonClicked);
    setWhatChatToShow(chatItem.id);
    setCurrentChat(chatItem);
  }

  function findUserName(id) {
    //this part needs help, wait for state to update (Works bcs workaround)
    if (id !== undefined && id !== 0) {
      console.log(id);
      console.log(currentGroupMembers);
      const usr = currentGroupMembers.find((e) => e.id === id);
      console.log(usr);
      return "h";
    } else {
      return "Error";
    }
  }

  const chatMessages = currentChat.messages.map((item) => {
    if (props.currentUser !== item.Sender) {
      return (
        <Flex
          key={item.orderNumber}
          background="red.200"
          width="fit-content"
          minWidth="100px"
          borderRadius="lg"
          p={3}
          alignSelf="flex-end"
        >
          <Text>
            {
              //currentGroupMembers.find((e) => e.id === item.Sender)}
            }
            From {item.Sender}: {item.message}
            {
              //current group member is not set when this runs, so it crashes
            }
          </Text>
        </Flex>
      );
    } else {
      return (
        <Flex
          key={item.orderNumber}
          background="green.200"
          width="fit-content"
          minWidth="100px"
          borderRadius="lg"
          p={3}
        >
          <Text>{item.message}</Text>
        </Flex>
      );
    }
  });

  function gree(element) {
    //return element.message.includes(props.userProfileInfoForUI.id);
    const currentUser = props.userProfileInfoForUI.id;
    const chatMembers = element.members;

    return chatMembers.includes(currentUser);
  }

  const chatsUserIsPartOf = chatData.filter(gree);
  const switchChatButtons = chatsUserIsPartOf.map((e) => {
    //not working bcs cant use inf in map?
    //console.log(e.members);
    // e.members.find(1);
    if (true) {
      return (
        <Button key={e.id} onClick={switchChat}>
          {" "}
          {e.title}{" "}
        </Button>
      );
    }
  });

  function navigateToProfile() {
    navigate("/profile");
  }

  function testFunction() {
    ApiCall.getData().then((e) => console.log(e));
    
    const post = {
      id: 6,
      location: "Front-End",
      comment: "Det funkar ju faktiskt från front-end också",
      help: true,
      present: false,
      localDateTime: "2022-12-15T20:34:11.079313"
      };
    ApiPost.postData(post).then((e) => console.log(e));
  }

  function updateMessage(event) {
    const textMessage = event.target.value;

    setMessageToSend(textMessage);
  }

  function sendMessage() {
    //var dateString = new Date().toISOString().substring(0,10);
    //function that sends message TODO
    const post = {
      fromUser: props.userProfileInfoForUI.id,
      fk_conversation: {id: whatChatToShow, name: "Chat with friends"},
      //fk_conversation: {id: 3, name: "Chat with friends"},
      messageText: messageToSend,

    };

    ApiPost.sendMessage(post).then((e)=> console.log(e))
    //function that gets the new messages TODO
    setMessageToSend("");
  }

  var requestLoop = setInterval(function(){
    console.log("hi")
   // ApiCall.getMessagesFromChat().then(e=> console.log(e))


  }, 6000000);

  if (!props.authorized) {
    //return <Navigate to="/" />;
  }
  //Made a const for condisonal rendering
  const chatWindow = (
    <Flex background="red.200" flex={10} width="100%" height="100vh">
      <Flex direction="column" background={"gray.100"} flex={100}>
        <Flex h="100px" background={"blue.100"} align="center" p={5}>
          <Avatar src="" />
          <Heading size="lg">{currentChat.title}</Heading>
        </Flex>

        <Flex
          direction="column"
          background={"gray.100"}
          flex={1}
          overflowX="scroll"
        >
          {chatMessages}
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
        position="fixed" //This one makes it overlap
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
        {switchChatButtons}
      </Flex>
      <Spacer />
      <Button
        className="toProfileButton"
        width="100%"
        colorScheme="blue"
        onClick={testFunction}
        mb={3}
      >
        {" "}
        Test button
      </Button>

      <Flex direction="column">{whatChatToShow !== 0 && chatWindow}</Flex>
    </Flex>
  );
}

{
  /* <Button
className="toProfileButton"
width="100%"
colorScheme="blue"
onClick={testFunction}
mb={3}
>
{" "}
Test button
</Button>



*/
}
