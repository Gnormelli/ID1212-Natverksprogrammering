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
export default function SignUp(props) {
  const { toggleColorMode } = useColorMode();
  const formBackground = useColorModeValue("gray.100", "gray.700");
  const navigate = useNavigate();

  function signUp() {
    //console.log("We try to log in sending ");
    //console.log(formData);




    if (
      formData.password === formData.confirmPassword &&
      !(formData.confirmPassword === "")
    ) {
      const post = {
        id: 0,
        username: formData.username,
        password: formData.password,
        email: "toBeDecided@gmail.com",
        profile_picture: "1",
        locked: false,
        enabled: true
      };
      ApiPost.createUser(post).then((e) => console.log(e));

      if (!usernameExist()) {
        //Create user in database TODO
        props.createProfileFunction(formData.username, formData.password);
        goToProfile();
      } else {
        console.log("Username alredy in use");
      }
    } else {
      console.log("Passwords dont match");
    }
  }

  function usernameExist() {
    //check if username esist with backend and return true if it does TODO
    return false;
  }

  const [formData, setFormData] = React.useState({
    username: "",
    password: "",
    confirmPassword: "",
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
    navigate("/");
  }

  function goToProfile() {
    navigate("/Profile");
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
        <Heading mb={6}>Create an account</Heading>
        <form>
          <p>
            <Input
              className="LogInPage--form"
              type="text"
              placeholder="Username"
              onChange={handelChange}
              name="username"
              mb={3}
              value={formData.firstName}
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
          <p>
            <Input
              className="LogInPage--form"
              type="password"
              placeholder="Confirm password"
              onChange={handelChange}
              name="confirmPassword"
              mb={3}
              value={formData.confirmPassword}
            />
          </p>
          <Button
            width="100%"
            colorScheme="blue"
            position="center"
            onClick={signUp}
            mb={3}
          >
            {" "}
            Sign up
          </Button>
          <Button
            variant="link"
            width="100%"
            colorScheme="blue"
            position="center"
            onClick={goToCreateAccount}
          >
            {" "}
            Alredy have an accont? Sign in
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
