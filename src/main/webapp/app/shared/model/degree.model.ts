import { IDepartment } from 'app/shared/model/department.model';

export interface IDegree {
  id?: string;
  name?: string;
  shortName?: string;
  departments?: IDepartment[];
}

export class Degree implements IDegree {
  constructor(public id?: string, public name?: string, public shortName?: string, public departments?: IDepartment[]) {}
}
