/**
 * interface for a chatmessage - createdDate and Id are server generated
 */
export interface ChatMessage {
  createdDate?: Date;
  message: string;
  id?: string;
  channelId: number;
}
