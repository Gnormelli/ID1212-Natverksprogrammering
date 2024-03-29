import React from "react";
import {
  Button,
  Flex,
  Heading,
  Input,
  Image,
  useColorModeValue,
} from "@chakra-ui/react";
import { useNavigate } from "react-router-dom";
import ApiPost from "../ApiInterface/ApiPost";
export default function SignUp(props) {
  const formBackground = useColorModeValue("gray.100", "gray.700");
  const navigate = useNavigate();

  function signUp() {

    if (
      formData.password === formData.confirmPassword &&
      !(formData.confirmPassword === "")
    ) {
      const post = {
        username: formData.username,
        password: formData.password
      };
      ApiPost.createUser(post).then((e) => {

        if (e.id === "Alredy a user by that name") {
          console.log("Username alredy in use")

        } else {
          props.createProfileFunction(formData.username, e.id);
          goToProfile();
        }
      });
    } else {
      console.log("Passwords dont match");
    }
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

      </Flex>
    </Flex>
  );
}
