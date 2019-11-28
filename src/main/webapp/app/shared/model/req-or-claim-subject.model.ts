import { IAlumniReqOrClaim } from 'app/shared/model/alumni-req-or-claim.model';

export interface IReqOrClaimSubject {
  id?: string;
  name?: string;
  decsription?: string;
  alumniReqOrClaims?: IAlumniReqOrClaim[];
}

export class ReqOrClaimSubject implements IReqOrClaimSubject {
  constructor(public id?: string, public name?: string, public decsription?: string, public alumniReqOrClaims?: IAlumniReqOrClaim[]) {}
}
