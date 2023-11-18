import React, { useState } from "react";

import valueNavbarItems from "./ValueNavbarItems";
import NavbarItemComponent from "./NavbarItemComponent";

import {
  Navbar,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
  Link,
  Button,
} from "@nextui-org/react";

interface NavbarComponentProps {
  selected: string;
}

const NavbarComponent = ({ selected }: NavbarComponentProps) => {
  const [selectedOption, setSelectedOption] = useState<string>(selected);

  const handleLinkClick = (option: string) => {
    setSelectedOption(option);
  };

  return (
    <Navbar>
      <NavbarBrand>
        <p className="font-bold text-inherit text-xl">DemograficAtlas</p>
      </NavbarBrand>
      <NavbarContent className="hidden sm:flex gap-4" justify="center">
        {valueNavbarItems.map((item, index) => {
          return (
            <NavbarItemComponent
              key={index}
              item={item}
              selectedOption={selectedOption}
              handleLinkClick={handleLinkClick}
            />
          );
        })}
      </NavbarContent>
      <NavbarContent justify="end">
        <NavbarItem>
          <Button as={Link} color="primary" href="#" variant="flat">
            Log out
          </Button>
        </NavbarItem>
      </NavbarContent>
    </Navbar>
  );
};

export default NavbarComponent;
