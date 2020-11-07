import { ChatMessage } from "./types";

const url = process.env.API_BASE_URL
  ? process.env.API_BASE_URL
  : "http://localhost:8080";

export async function addMessage(item: ChatMessage) {
  const data = await fetch(`${url}/chats`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(item),
  });
  return data.status;
}
