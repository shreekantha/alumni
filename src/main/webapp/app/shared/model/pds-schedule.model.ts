import { IUser } from 'app/core/user/user.model';
import { IPdsEnrollment } from 'app/shared/model/pds-enrollment.model';
import { Moment } from 'moment';

export interface IPdsSchedule {
  id?: string;
  duration?: any;
  date?: Moment;
  time?: Moment;
  venue?: string;
  remarks?: string;
  pdsEnrollments?: IPdsEnrollment[];
  users?: IUser[];
  profDevServiceName?: string;
  profDevServiceId?: string;
}

export class PdsSchedule implements IPdsSchedule {
  constructor(
    public id?: string,
    public duration?: any,
    public date?: Moment,
    public time?: Moment,
    public venue?: string,
    public remarks?: string,
    public pdsEnrollments?: IPdsEnrollment[],
    public users?: IUser[],
    public profDevServiceName?: string,
    public profDevServiceId?: string
  ) {}
}
