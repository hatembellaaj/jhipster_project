export interface ICheval {
  id?: number;
  name?: string | null;
  weight?: number | null;
}

export class Cheval implements ICheval {
  constructor(public id?: number, public name?: string | null, public weight?: number | null) {}
}

export function getChevalIdentifier(cheval: ICheval): number | undefined {
  return cheval.id;
}
