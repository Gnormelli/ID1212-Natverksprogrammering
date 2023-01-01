import React from "react";
import {
  Button,
  Flex,
  Heading,
  Input,
  Image,
  useColorMode,
  useColorModeValue,
} from "@chakra-ui/react";
import { useNavigate } from "react-router-dom";
import ApiPost from "../ApiInterface/ApiPost";
import ApiCall from "../ApiInterface/ApiCall";
import {type} from "@testing-library/user-event/dist/type";

export default function LogInPage(props) {
  const { toggleColorMode } = useColorMode();
  const formBackground = useColorModeValue("gray.100", "gray.700");
  const navigate = useNavigate();
  //const [jwt, setJwt] = useLocalState("", "jwt")

  const [formData, setFormData] = React.useState({
    username: "",
    password: "",
  });

  function handelChange(event) {
    const { name, value } = event.target;
    setFormData((prevValues) => {
      return {
        ...prevValues,
        [name]: value,
      };
    });
  }

  function goToCreateAccount() {
    navigate("/SignUp");
  }
  function logIn() {
    //ApiCall.getData().then((e) => console.log(e));

    const post = {
      id: 20,
      username: formData.username,
      password: formData.password,
      email: "hi@gmail.com",
      locked: false,
      enabled: true
    };
    ApiPost.tryToLogIn(post).then((e) => {
      if(e.id === "Bad credentials"){
        console.log(e.id)
      }else{

        props.setFullUserGlobal({"id": formData.username, "profilePictureID": e.profilePicture.id, "theRealID": e.id})
        navigate("/Profile");
      }
    });

  }

  return (
    <Flex
      height="100vh"
      alignItems="center"
      justifyContent="center"
      backgroundColor="orange.400"
    >
      <Flex direction="column" background={formBackground} p={12} rounded={6}>
        <Image src="/pictures/chaticon.png" boxSize="300px" />
        <Heading mb={6}>Log in</Heading>
        <form>
          <p>
            <Input
              className="LogInPage--form"
              type="text"
              placeholder="Username"
              onChange={handelChange}
              name="username"
              mb={3}
              value={formData.username}
            />
          </p>
          <p>
            <Input
              className="LogInPage--form"
              type="password"
              placeholder="Password"
              onChange={handelChange}
              name="password"
              mb={3}
              value={formData.password}
            />
          </p>
          <Button
            width="100%"
            colorScheme="blue"
            position="center"
            onClick={logIn}
            mb={3}
          >
            {" "}
            Log in
          </Button>
          <Button
            variant="link"
            width="100%"
            colorScheme="blue"
            position="center"
            onClick={goToCreateAccount}
          >
            {" "}
            Create an account
          </Button>
        </form>

        {/* <Button colorScheme="blue" onClick={toggleColorMode}>
          {" "}
          DarkMode
        </Button> */}
      </Flex>
    </Flex>
  );
}
