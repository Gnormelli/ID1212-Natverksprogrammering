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
  const [whatChatToShow, setWhatChatToShow] = React.useState(0);
  const [whatGroupToShow, setWhatGroupToShow] = React.useState(1);
  const [messageToSend, setMessageToSend] = React.useState("");
  const [messagesToShow, setMessagesToShow] = React.useState();
  const [chatsUserIsPartOf, setChatsUserIsPartOf] = React.useState();
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

    getChatUserIsPartOf()
  }, []);


  React.useEffect(() => {

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

      const usr = currentGroupMembers.find((e) => e.id === id);

      return "h";
    } else {
      return "Error";
    }
  }



  function gree(element) {
    //return element.message.includes(props.userProfileInfoForUI.id);
    const currentUser = props.userProfileInfoForUI.id;
    const chatMembers = element.members;

    return chatMembers.includes(currentUser);
  }


  function getChatUserIsPartOf(){
    const post = {
      id: props.userProfileInfoForUI.theRealID
    };

    ApiPost.getChatsUserIsPartOf(post).then(e=> {

      const chats = e.map((el) => {

          return (
              <Button key={el.id} onClick={switchChat}>
                {" "}
                {el.name}{" "}
              </Button>
          );

      })
      setWhatGroupToShow(chats)
    })

  }
  const chatsUserIsPartOf1 = chatData.filter(gree);


  function navigateToProfile() {
    navigate("/profile");
  }

  function getMessages() {
    const post = {
      fk_conversation: {id: 1, name: "hold"}
    };
    ApiPost.getMessagesFromChat(post).then(e=> {
      console.log(e)
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
    const post = {
      fromUser: props.userProfileInfoForUI.id,
      fk_conversation: {id: whatChatToShow, name: "Chat with friends"},
      messageText: messageToSend,
    };
    ApiPost.sendMessage(post).then(e =>getMessages())
    setMessageToSend("");
  }


  var requestLoop = setInterval(function(){ //Dont remove
    const post = {
      fk_conversation: {id: 1, name: "hold"}
    };
    ApiPost.getMessagesFromChat(post).then(e=> {
      console.log(e)
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


  }, 1000000);



  var ddd = setInterval(function(){ //Dont remove

    console.log("ga")

  }, 5000);


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
              From {item.Sender}: {item.message}
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

  function test(){
    getChatUserIsPartOf()
  }

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
        {whatGroupToShow}
      </Flex>
      <Spacer />

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
