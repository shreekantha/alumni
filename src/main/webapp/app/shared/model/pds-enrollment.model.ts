import { PdsSchedule } from './pds-schedule.model';

export interface IPdsEnrollment {
  id?: string;
  remarks?: string;
  enrolledByFirstName?: string;
  enrolledById?: string;
  enrolledToId?: string;
  enrolledTo?:PdsSchedule
}

export class PdsEnrollment implements IPdsEnrollment {
  constructor(
    public id?: string,
    public remarks?: string,
    public enrolledByFirstName?: string,
    public enrolledById?: string,
    public enrolledToId?: string,
    public enrolledTo?:PdsSchedule
  ) {}
}
