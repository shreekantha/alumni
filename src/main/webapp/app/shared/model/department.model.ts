export interface IDepartment {
  id?: string;
  name?: string;
  shortName?: string;
  degreeName?: string;
  degreeId?: string;
}

export class Department implements IDepartment {
  constructor(public id?: string, public name?: string, public shortName?: string, public degreeName?: string, public degreeId?: string) {}
}
