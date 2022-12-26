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

export default function LogInPage(props) {
  const { toggleColorMode } = useColorMode();
  const formBackground = useColorModeValue("gray.100", "gray.700");
  const navigate = useNavigate();
  //const [jwt, setJwt] = useLocalState("", "jwt")

  function logIn() {
    console.log("Sending request to login");
    console.log(formData);
    ApiPost.postLoginInformation(formData).then((e) => console.log(e));
  }

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
