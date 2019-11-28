import { IAlumniMeetReq } from 'app/shared/model/alumni-meet-req.model';

export interface IMeetReqTopic {
  id?: string;
  name?: string;
  description?: string;
  alumniMeetReqs?: IAlumniMeetReq[];
}

export class MeetReqTopic implements IMeetReqTopic {
  constructor(public id?: string, public name?: string, public description?: string, public alumniMeetReqs?: IAlumniMeetReq[]) {}
}
