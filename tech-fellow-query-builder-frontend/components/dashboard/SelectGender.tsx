import React from "react";
import { RadioGroup, Radio } from "@nextui-org/react";

const SelectGender = () => {
  return (
    <div>
      <div className="text-center mt-3 font-bold text-lg">
        Choose the gender
      </div>
      <div>
        <RadioGroup label="Select your Gender">
          <Radio value="male">Male</Radio>
          <Radio value="famale">Famale</Radio>
        </RadioGroup>
      </div>
    </div>
  );
};

export default SelectGender;
