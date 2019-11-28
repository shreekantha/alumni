import { AlumniMeetReqStatus } from 'app/shared/model/enumerations/alumni-meet-req-status.model';

export interface IAlumniMeetReq {
  id?: string;
  aboutMe?: string;
  description?: string;
  documentContentType?: string;
  document?: any;
  status?: AlumniMeetReqStatus;
  requestByFirstName?: string;
  requestById?: string;
  requestToFirstName?: string;
  requestToId?: string;
  topicName?: string;
  topicId?: string;
  aspiredRoleName?: string;
  aspiredRoleId?: string;
}

export class AlumniMeetReq implements IAlumniMeetReq {
  constructor(
    public id?: string,
    public aboutMe?: string,
    public description?: string,
    public documentContentType?: string,
    public document?: any,
    public status?: AlumniMeetReqStatus,
    public requestByFirstName?: string,
    public requestById?: string,
    public requestToFirstName?: string,
    public requestToId?: string,
    public topicName?: string,
    public topicId?: string,
    public aspiredRoleName?: string,
    public aspiredRoleId?: string
  ) {}
}
