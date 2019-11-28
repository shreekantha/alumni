export interface IPdsCourseTopic {
  id?: string;
  name?: string;
  description?: string;
  duration?: number;
  pdsCourseCourseName?: string;
  pdsCourseId?: string;
}

export class PdsCourseTopic implements IPdsCourseTopic {
  constructor(
    public id?: string,
    public name?: string,
    public description?: string,
    public duration?: number,
    public pdsCourseCourseName?: string,
    public pdsCourseId?: string
  ) {}
}
