import { Moment } from 'moment';
import { ReqOrClaimStatus } from 'app/shared/model/enumerations/req-or-claim-status.model';

export interface IAlumniReqOrClaim {
  id?: string;
  requestFor?: string;
  reason?: string;
  status?: ReqOrClaimStatus;
  requestedDate?: Moment;
  requestByFirstName?: string;
  requestById?: string;
  assigneeFirstName?: string;
  assigneeId?: string;
  subjectName?: string;
  subjectId?: string;
}

export class AlumniReqOrClaim implements IAlumniReqOrClaim {
  constructor(
    public id?: string,
    public requestFor?: string,
    public reason?: string,
    public status?: ReqOrClaimStatus,
    public requestedDate?: Moment,
    public requestByFirstName?: string,
    public requestById?: string,
    public assigneeFirstName?: string,
    public assigneeId?: string,
    public subjectName?: string,
    public subjectId?: string
  ) {}
}
