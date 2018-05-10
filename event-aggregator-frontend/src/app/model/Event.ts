export class Event {
  id: number;
  title: string;
  type: string;
  description: string;
  startTime: Date;
  endTime: Date;
  externalLink: string;
  emailNotification: boolean;
  user: any;
}
