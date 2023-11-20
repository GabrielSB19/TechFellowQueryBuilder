"use client";

import React, { useState } from "react";
import Link from "next/link";
import { Input } from "@nextui-org/input";
import { Button } from "@nextui-org/react";
import userService from "@/service/userService";

const FormUser = () => {
  const [username, setUsername] = useState<string>("");

  const handleUsername = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(e.target.value);
  };

  const handleClear = () => {
    setUsername("");
  };

  const handleCreateUser = async (username: string) => {
    try {
      await userService.fetchCreateData(username);
      localStorage.setItem("username", username);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="flex flex-col justify-center items-center mt-8">
      <div className="text-lg font-bold">¿Do you want to use our app?</div>
      <div className="flex w-full flex-col md:flex-nowrap gap-4 mt-4">
        <Input
          isClearable
          type="text"
          onChange={handleUsername}
          label="Username"
          value={username}
          onClear={handleClear}
          className="max-w-xs"
        />
        {username.length > 2 && (
          <Link href="/dashboard" className="w-[100%]">
            <Button
              color="primary"
              className="animate-appearance-in mt-2 w-full"
              onClick={() => {
                handleCreateUser(username);
              }}
            >
              Log In
            </Button>
          </Link>
        )}
      </div>
    </div>
  );
};

export default FormUser;
