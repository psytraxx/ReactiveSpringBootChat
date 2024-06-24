import type { ChatMessage } from './types';

export async function addMessage(item: ChatMessage) {
  const url = getBaseUrl();
  const data = await fetch(`${url}/chats`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(item)
  });
  return data.status;
}

// Fixed typo in "processs" and simplified environment variable check
export function getBaseUrl(): string {
  // Corrected the typo from "processs" to "process"
  if (typeof process !== 'undefined') {
    return process.env.API_BASE_URL || 'http://localhost:8080';
  }
  return 'http://localhost:8080';
}
