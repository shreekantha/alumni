import { IPdsCourse } from 'app/shared/model/pds-course.model';
import { IPdsSchedule } from 'app/shared/model/pds-schedule.model';

export interface IProfDevService {
  id?: string;
  name?: string;
  description?: string;
  pdsCourses?: IPdsCourse[];
  pdsSchedules?: IPdsSchedule[];
}

export class ProfDevService implements IProfDevService {
  constructor(
    public id?: string,
    public name?: string,
    public description?: string,
    public pdsCourses?: IPdsCourse[],
    public pdsSchedules?: IPdsSchedule[]
  ) {}
}
