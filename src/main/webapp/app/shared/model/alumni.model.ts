export interface IAlumni {
  id?: string;
  usn?: string;
  name?: string;
  email?: string;
  mobile?: string;
  degree?: string;
  specialization?: string;
  jobPosition?: string;
  company?: string;
  yearOfGraduation?: string;
  experience?: number;
}

export class Alumni implements IAlumni {
  constructor(
    public id?: string,
    public usn?: string,
    public name?: string,
    public email?: string,
    public mobile?: string,
    public degree?: string,
    public specialization?: string,
    public jobPosition?: string,
    public company?: string,
    public yearOfGraduation?: string,
    public experience?: number
  ) {}
}
