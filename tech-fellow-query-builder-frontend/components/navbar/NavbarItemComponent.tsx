import React, { useState } from "react";

import {
  Navbar,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
  Link,
  Button,
} from "@nextui-org/react";

type NavbarItemValue = {
  option: string;
  link: string;
};

interface NavbarItemComponentProps {
  item: NavbarItemValue;
  selectedOption: string;
  handleLinkClick: (option: string) => void;
}

const NavbarItemComponent = ({
  item,
  selectedOption,
  handleLinkClick,
}: NavbarItemComponentProps) => {
  return (
    <NavbarItem>
      <Link
        color="foreground"
        href={item.link}
        onClick={() => handleLinkClick(item.option)}
      >
        <p
          className={`text-xl ${
            selectedOption === item.option && "text-blue-500 font-bold"
          }`}
        >
          {item.option}
        </p>
      </Link>
    </NavbarItem>
  );
};

export default NavbarItemComponent;
