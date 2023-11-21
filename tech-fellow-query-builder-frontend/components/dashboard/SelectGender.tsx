import React, { useState, useEffect } from "react";
import { RadioGroup, Radio } from "@nextui-org/react";

interface SelectGenderProps {
  handleProperty: (property: string, value: string) => void;
  paramsGender: string;
}

const SelectGender = ({ handleProperty, paramsGender }: SelectGenderProps) => {
  const [selected, setSelected] = useState("");

  useEffect(() => {
    handleProperty("gender", selected);
  }, [selected]);

  useEffect(() => {
    if (paramsGender) setSelected(paramsGender);
  }, []);

  return (
    <div>
      <div className="text-center mt-3 font-bold text-lg">
        Choose the gender
      </div>
      <div className="flex flex-row justify-center">
        <RadioGroup
          label="Select your Gender"
          value={selected}
          onValueChange={setSelected}
        >
          <Radio value="MA">Male</Radio>
          <Radio value="FE">Female</Radio>
          <Radio value="TO">All</Radio>
        </RadioGroup>
      </div>
    </div>
  );
};

export default SelectGender;
