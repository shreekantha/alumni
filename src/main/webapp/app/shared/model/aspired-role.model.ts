import { IAlumniMeetReq } from 'app/shared/model/alumni-meet-req.model';

export interface IAspiredRole {
  id?: string;
  name?: string;
  description?: string;
  alumniMeetReqs?: IAlumniMeetReq[];
}

export class AspiredRole implements IAspiredRole {
  constructor(public id?: string, public name?: string, public description?: string, public alumniMeetReqs?: IAlumniMeetReq[]) {}
}
