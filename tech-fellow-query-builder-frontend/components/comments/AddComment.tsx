import React from "react";
import { Button, Textarea } from "@nextui-org/react";

interface AddCommentProps {
  isCommentCreating: boolean;
  setIsCommentCreating: (value: boolean) => void;
}

const AddComment = ({
  isCommentCreating,
  setIsCommentCreating,
}: AddCommentProps) => {
  return (
    <div className="flex items-center justify-between w-full">
      <div className="flex-grow pr-4">
        <Textarea
          label="Comment"
          placeholder="Enter your comment"
          className="w-full"
        />
      </div>
      <div>
        <Button color="primary">Submit</Button>
      </div>
    </div>
  );
};

export default AddComment;
