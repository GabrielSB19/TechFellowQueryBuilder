import FormUser from "@/components/FormUser";

export default function Home() {
  return (
    <section className="flex flex-col items-center justify-center gap-4 py-8 md:py-10">
      <div className="inline-block max-w-lg text-center justify-center">
        <div className="text-5xl mb-3">
          Welcome to{" "}
          <span className="font-bold text-blue-500">DemograficAtlas</span>
        </div>
        <div className="font-bold">Mapping the World, Understanding People</div>
      </div>
      <FormUser />
    </section>
  );
}
