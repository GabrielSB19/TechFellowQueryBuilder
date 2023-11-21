import React from "react";
import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Divider,
  Link,
  Avatar,
} from "@nextui-org/react";
import CommentType from "@/types/CommentType";

interface CardCommentProps {
  comment: CommentType;
}

const CardComment = ({ comment }: CardCommentProps) => {
  return (
    <Card>
      <CardHeader className="flex gap-3">
        <Avatar showFallback src="https://images.unsplash.com/broken" />
        <div className="flex flex-col">
          <p className="text-md">{comment.username}</p>
          <p className="text-small text-default-500">{comment.userClient}</p>
        </div>
      </CardHeader>
      <Divider />
      <CardBody>
        <p>{comment.comment}</p>
      </CardBody>
      <Divider />
    </Card>
  );
};

export default CardComment;
